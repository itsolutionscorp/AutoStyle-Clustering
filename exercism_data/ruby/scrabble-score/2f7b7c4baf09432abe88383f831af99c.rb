class Scrabble

  def initialize(word)
    @scores = get_word_scores_array
    @word = word
  end

  def score
    score = 0
    @word.to_s.gsub(/\s/, "").split("").each do |char|
      score += @scores[char.upcase] # if @word.include?(char)
    end
    score
  end

  def self.score(word)
    self.new(word).score
  end

  def get_word_scores_array
    scrabble_arr = {
    ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'] => 1,
    ['D', 'G'] => 2,
    ['B', 'C', 'M', 'P'] => 3,
    ['F', 'H', 'V', 'W', 'Y'] => 4,
    ['K'] => 5,
    ['J', 'X'] => 8,
    ['Q', 'Z'] => 10
    }

    updated_arr = {}
    scrabble_arr.each do |k,v|
      k.each do |letter|
        updated_arr[letter] = v
      end
    end
    updated_arr
  end
end
