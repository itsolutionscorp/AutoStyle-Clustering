module PigLatin
  CONSONANTS = %w(bl br b chr ch cl cr c dh dj d f gh g h j kn k
    ll l mn m n phr ps p qu r sch shr squ sc sh s thr th tr tw t
    v wh wr w x y z)

  EXCEPTIONS = Hash.new do |hash, key|
    case key
    when "y"
      ["yt"]
    when "x"
      ["xr"]
    else
      []
    end
  end

  def self.translate(in_string)
    in_string.scan(/\w+/).map { |word|
      move_consonant_to_the_end_of(word) + "ay"
    }.join " "
  end

  private
  def self.move_consonant_to_the_end_of(word)
    if consonant = leading_consonant(word)
      word.slice(consonant.length, word.length) + consonant
    else
      word
    end
  end

  def self.leading_consonant(word)
    CONSONANTS.find do |consonant|
      word.start_with?(consonant) &&
        !EXCEPTIONS[consonant].any? do |excepted|
          word.start_with?(excepted)
        end
    end
  end
end
