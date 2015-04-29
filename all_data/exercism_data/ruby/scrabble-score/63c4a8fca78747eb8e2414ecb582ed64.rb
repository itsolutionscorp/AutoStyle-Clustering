class Scrabble
  def initialize(word)
    @word = (word) ? word.downcase.scan(/\w+/) : ""
  end

  def score
    ans = 0
    return ans if @word.empty?

    dict = {
      "a" => 1,
      "e" =>1,
      'i' =>1,
      'o' =>1,
      'u'=>1,
      'l' =>1,
      'n' =>1,
      'r' => 1,
      's'=>1,
      't'=>1,
      'd'=>2,
      'g'=>2,
      'b'=>3,
      'c'=>3,
      'm'=>3,
      'p'=>3,
      'f'=>4,
      'h'=>4,
      'v'=>4,
      'w'=>4,
      'y'=>4,
      'k'=>5,
      'j'=>8,
      'x'=>8,
      'q'=>10,
      'z'=>10
    }

    for i in @word.join.split("")
      ans += dict[i]
    end
    return ans
  end

  def self.score(word)
    Scrabble.new(word).score
  end
end
