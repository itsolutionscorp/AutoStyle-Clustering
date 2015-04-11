class Raindrops
  DICTIONARY = {
    7 => "Plong",
    5 => "Plang",
    3 => "Pling"
  }

  def self.convert(i)
    new(i).convert!
  end

  def initialize(i)
    @i = i
    @s = ''
  end

  def convert!
    DICTIONARY.each { |n, w|
      if @i % n == 0
        @s = w + @s unless @s.include?(w)
        break @i /= n
      end
    } === DICTIONARY ? finalize! : send(__method__)
  end

  private

  def finalize!
    @s.empty? ? @i.to_s : @s
  end
end
