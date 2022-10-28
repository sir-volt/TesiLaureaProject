package input

import model.Series

trait SeriesReader[S <: Series[_, _]] {
  def read : S
}
