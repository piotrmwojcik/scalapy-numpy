package me.shadaj.scalapy.numpy

import me.shadaj.scalapy.py
import me.shadaj.scalapy.readwrite.Writer
import me.shadaj.scalapy.py.SeqConverters

import scala.language.implicitConversions

@py.native
trait PythonSeq[K] extends py.Object
object PythonSeq {
  implicit def seqToPythonSeq[K <: py.Any](seq: Seq[K]): PythonSeq[K] = {
    seq.toPythonProxy.as[PythonSeq[K]]
  }

  def empty[K <: py.Any]: PythonSeq[K] = seqToPythonSeq(Seq.empty[K])

  def emptyString: PythonSeq[String] = Seq.empty[String].toPythonCopy.as[PythonSeq[String]]
}
