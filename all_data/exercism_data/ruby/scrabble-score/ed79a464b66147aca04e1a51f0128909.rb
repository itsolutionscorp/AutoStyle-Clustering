class Scrabble
  def initialize(str)
    @str = str
    clean_str if @str
  end

  def key
    {
      "AEIOULNRST" => 1,
      "DG" => 2,
      "BCMP" => 3,
      "FHVWY" => 4,
      "K" => 5,
      "JX" => 8,
      "QZ" => 10
    }
  end

  def score
    total = 0
    return total if @str == nil
    keys = key
    @str.each_char do |letter|
      keys.each { |k, v| total += v if k.include?(letter) }
    end
    total
  end

  def self.score(str)
    Scrabble.new(str).score
  end

  def clean_str
    str = @str.gsub(/\W/, "").upcase
    @str = str
  end

end
