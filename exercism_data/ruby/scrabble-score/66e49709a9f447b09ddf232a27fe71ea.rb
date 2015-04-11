class Scrabble

  def self.score(word)
    new(word).score
  end

  attr_reader :word

  def initialize(word)
    @word = if word
              word.downcase.gsub(/\s/, '')
            else
              ""
            end
  end

  def letters_map
    @letters_map ||= (
      [
        [ %w(A E I O U L N R S T), 1 ],
        [ %w(D G)                , 2 ],
        [ %w(B C M P)            , 3 ],
        [ %w(F H V W Y)          , 4 ],
        [ %w(K)                  , 5 ],
        [ %w(J X)                , 8 ],
        [ %w(Q Z)                , 10 ]
      ].
      each_with_object(Hash.new(0)) do |(letters, score), result|
        letters.each do |letter|
          result[letter.downcase] = score
        end
      end
    )
  end

  def score
    return 0 if word.empty?
    word.chars.map { |c| letters_map[c] }.inject(&:+)
  end

end
