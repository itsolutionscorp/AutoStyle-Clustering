class RomanNumeral
	def initialize(arabic)
		@arabic = arabic
	end

	def to_s
		arabic = @arabic

		Conversion.all.inject('') do |res, conversion|
			if prefix = conversion.next_prefixable(arabic)
				res += prefix.roman
				arabic += prefix.arabic
			end

			while arabic >= conversion.arabic
				res += conversion.roman
				arabic -= conversion.arabic
			end

			res
		end
	end
end

class RomanNumeral::Conversion
  attr_reader :arabic, :roman

  def self.all
    [
      new(1_000, 'M'),
      new(  500, 'D'),
      new(  100, 'C'),
      new(   50, 'L'),
      new(   10, 'X'),
      new(    5, 'V'),
      new(    1, 'I'),
    ]
  end

  def self.from(start)
    self.all.find_all{|c| c.arabic <= start }
  end

  def initialize(arabic, roman)
    @arabic, @roman = arabic, roman
  end

  def next_prefixable(target)
    return nil unless target < arabic

    if next_subtractable && target + next_subtractable.arabic >= arabic
      next_subtractable
    end
  end

  def next_subtractable
    @next_subtractable ||= self.class.from(arabic-1).find(&:subtractable?)
  end

  def subtractable?
    i = 1
    i *= 10 while i < arabic
    i == arabic
  end
end

class Integer
	def to_roman
		RomanNumeral.new(self).to_s
	end
end
