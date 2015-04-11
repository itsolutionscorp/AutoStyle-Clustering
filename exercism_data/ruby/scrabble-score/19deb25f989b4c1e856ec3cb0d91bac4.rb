class Scrabble
  @@points = {
    "aeioulnrst" => 1,
    "dg" => 2,
    "bcmp" => 3,
    "fhvwy" => 4,
    "k" => 5,
    "jx" => 8,
    "qz" => 10,
  }

  def initialize(word)
    @word = word ? word.gsub(/\s+/, "").downcase : ""
  end

  def score
    unless @word.nil? || @word.empty?
      @word.chars.map do |letter|
        @@points[@@points.keys.select { |k| k.include?(letter) }.first]
      end.reduce(:+)
    else
      0
    end
  end

  def self.score(word)
    new(word).score
  end
end
