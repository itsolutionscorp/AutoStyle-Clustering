# gigasecond.rb
#
# J Marks
#
# Solution to exercism.io gigasecond exercise

require "date"

# Library providing method to calculate a Date one billion seconds (1 Gs) in the
# future of a given reference date
module Gigasecond
  extend self

  # This quantity is compatible w/Date's native + operation that works with
  # days. It does NOT precisely match 1 Gs 
  DAYS_IN_GIGASECOND = 10 ** 9 / 86400

  # from
  # Parameters
  #   reference_date (Date) A Date 
  # Returns: (Date) The date on which elapses 1 gigasecond from the reference
  # Computes a date one billion seconds in advance of the reference input
  def from(reference_date)
    # Note that DAYS_IN_GIGASECOND is smaller than 1 Gs, but by less than one
    # day. Because this method concerns only Dates (as in anniversaries) and
    # not Time the fractional precision is unimportant.
    reference_date + DAYS_IN_GIGASECOND
  end
end
