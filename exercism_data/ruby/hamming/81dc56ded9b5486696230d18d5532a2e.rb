#!/usr/bin/env ruby

class Hamming
  attr_reader :source, :dest

  def self.compute(source, dest)
    @count = 0
    @source = source
    @dest = dest

    if @source.length < @dest.length
      @lesser_length = @source.length
    else
      @lesser_length = @dest.length
    end

    0.upto(@lesser_length-1) do |x|
      if @source[x] != @dest[x]
        @count = @count + 1
      end
    end

    return @count
  end
end

source = "GAGCCTACTAACGGGAT"
dest = "CATCGTAATGACGGCCT"

puts
puts source
puts dest

distance = Hamming.compute(source, dest)
puts "Hamming distance of the above strings is: #{distance}"
puts
