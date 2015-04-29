class Scrabble
  def initialize(word_string)
    @word_string = word_string
  end

  def score
    score_array = {1 => %w{A E I O U L N R S T}},
      {2 => %w{D G}},
      {3 => %w{B C M P}},
      {4 => %w{F H V W Y }},
      {5 => %w{K}},
      {8 => %w{J X}},
      {10 => %w{Q Z}}
    if @word_string == nil || @word_string.strip == ''
      0
    else
      storage = Hash.new(0)
      @word_string.split(//).each do |letter|
        storage[letter] += 1
        @storage_upcase = {}
        storage.each do |l, v|
          @storage_upcase[l.upcase] = v
        end
      end
      @total_sum = 0
      @storage_upcase.each do |char, count|
        score_array.each do |hash|
          hash.each do |point, value|
            value.each do |let|
              if let.match(char)
                @sum = point * count
                @total_sum += @sum
              end
            end
          end
        end
      end
      @total_sum
    end
  end
end
