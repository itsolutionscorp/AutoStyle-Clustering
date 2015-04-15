require 'pry'

class Series
  attr_reader :integers

  def initialize(string)
    @integers = string.split(//).collect {|i| i.to_i}
  end

  def slices(length)
    raise ArgumentError if length > integers.size

    result = []

    integers.each_index do |index|
      slice = integers.slice(index, length)

      next if slice.size < length

      result << slice
    end

    return result
  end
end
