package me.shadaj.scalapy.numpy

import me.shadaj.scalapy.interpreter.PyValue
import me.shadaj.scalapy.py
import me.shadaj.scalapy.readwrite.{Reader, Writer}

class NDArray[T](val value: PyValue)(implicit reader: Reader[T]) extends py.Object with Seq[T] {
  private val origDynamic = this.as[py.Dynamic]

  def tolist: py.Any = origDynamic.tolist()

  def unary_-(): NDArray[T] = (-origDynamic).as[NDArray[T]]

  def +(o: T)(implicit writer: Writer[T]): NDArray[T] = (origDynamic + o).as[NDArray[T]]
  def +(o: NDArray[T])(implicit writer: Writer[NDArray[T]]): NDArray[T] = (origDynamic + o).as[NDArray[T]]

  def -(o: T)(implicit writer: Writer[T]): NDArray[T] = (origDynamic - o).as[NDArray[T]]
  def -(o: NDArray[T])(implicit writer: Writer[NDArray[T]]): NDArray[T] = (origDynamic - o).as[NDArray[T]]

  def *(o: T)(implicit writer: Writer[T]): NDArray[T] = (origDynamic * o).as[NDArray[T]]
  def *(o: NDArray[T])(implicit writer: Writer[NDArray[T]]): NDArray[T] = (origDynamic * o).as[NDArray[T]]

  def /(o: T)(implicit writer: Writer[T]): NDArray[T] = (origDynamic / o).as[NDArray[T]]
  def /(o: NDArray[T])(implicit writer: Writer[NDArray[T]]): NDArray[T] = (origDynamic / o).as[NDArray[T]]

  def T(implicit writer: Writer[T]): NDArray[T] = origDynamic.T.as[NDArray[T]]

  def astype(newType: NumPyType): NDArray[T] = origDynamic.astype(newType).as[NDArray[T]]

  def reshape(shape: PythonSeq[Int]): NDArray[T] = origDynamic.reshape(shape).as[NDArray[T]]

  def shape: Seq[Int] = origDynamic.shape.as[Seq[Int]]

  override def length: Int = py.Dynamic.global.len(this).as[Int]

  override def apply(idx: Int): T = origDynamic.arrayAccess(idx).as[T]

  override def iterator: Iterator[T] = (0 until length).toIterator.map(apply)
}

object NDArray {
  implicit def reader[T](implicit reader: Reader[T]): Reader[NDArray[T]] = new Reader[NDArray[T]] {
    override def read(v: PyValue): NDArray[T] = new NDArray[T](v)(reader)
  }
}
