package input

import model.TimeSeries

trait SeriesReader[S <: TimeSeries[_, _]] {
  def read : S
}
