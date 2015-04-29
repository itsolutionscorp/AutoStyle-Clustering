class Hamming
	def self.compute str, str2
    short, long = (str.length > str2.length ? [str2, str] : [str, str2])
    
    diff = 0
    short.each_char.with_index do |c,i|
      unless long[i] == c
        diff += 1
      end
    end
    Result.new(long, short, diff).diff
  end

  class Result
    attr_accessor :long, :short, :diff

    def initialize long, short, diff
      self.long  = long
      self.short = short
      self.diff  = diff
    end

    def to_s
      puts "Longest: #{long}"
      puts "Shorter: #{short}"
      puts "Diff: #{diff}"
    end
  end
end
puts Hamming.compute('AGAGACTTA', 'AAA').to_s
