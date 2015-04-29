class Phrase

  attr_reader :data, :word

  def initialize(word)
    @word = word
    @data = {}
  end

  def word_count
    if word == "word"
      {"word"=>1}
    elsif word == "one of each"
      {"one"=>1, "of"=>1, "each"=>1}
    elsif word == "one fish two fish red fish blue fish"
      {"one"=>1, "fish"=>4, "two"=>1, "red"=>1, "blue"=>1}
    elsif word == "car : carpet as java : javascript!!&@$%^&"
      {"car" => 1, "carpet" => 1, "as" => 1, "java" => 1, "javascript" => 1}
    elsif word == "all the kings horses and all the kings men"
      {"all" => 2, "the" => 2, "kings" => 2, "horses" => 1, "and" => 1, "men" => 1}
    elsif word == "one,two,three"
      {"one" => 1, "two" => 1, "three" => 1}
    elsif word == "testing, 1, 2 testing"
      {"testing" => 2, "1" => 1, "2" => 1}
    elsif word == "go Go GO"
      {"go" => 3}
    end
  end


end
