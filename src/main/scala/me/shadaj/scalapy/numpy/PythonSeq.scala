package me.shadaj.scalapy.numpy

import me.shadaj.scalapy.py
import me.shadaj.scalapy.readwrite.Writer
import me.shadaj.scalapy.py.SeqConverters

import scala.language.implicitConversions

@py.native
trait PythonSeq[K] extends py.Object
object PythonSeq {
  implicit def seqToPythonSeq[K <: py.Any](seq: Seq[K]): PythonSeq[K] = {
    seq.toPythonCopy.as[PythonSeq[K]]
  }

  def empty[K <: py.Any]: PythonSeq[K] = seqToPythonSeq(Seq.empty[K])

  def emptyString: PythonSeq[String] = Seq.empty[String].toPythonCopy.as[PythonSeq[String]]

  implicit def seqString(seq: Seq[String]): PythonSeq[String] = {
    seq.toPythonCopy.as[PythonSeq[String]]
  }

  implicit def seqInt(seq: Seq[Int]): PythonSeq[Int] = {
    seq.toPythonCopy.as[PythonSeq[Int]]
  }

  implicit def seqTuple[K <: py.Any, S <: py.Any](seq: Seq[(K, S)]): PythonSeq[(K, S)] = {
    seq.toPythonCopy.as[PythonSeq[(K, S)]]
  }
}
