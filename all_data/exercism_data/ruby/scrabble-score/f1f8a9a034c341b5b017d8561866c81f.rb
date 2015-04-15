## Tests completed in 22 minutes, 25 minutes after clean-up
# Much of that time getting table, then remembering it was in the ETL
# codebase exactly!  Still kept it in the easy format.  I feel that's a
# bit more readible.
#
class Scrabble
  
  def initialize(word)
    @word = word.to_s.downcase
    @letter_lookup = ETL.table
  end

  def score
    return 0 if (@word.nil? or @word.empty?)
    values = @word.chars.map{|char| @letter_lookup.fetch(char,0)}
    sum = values.inject(:+)
  end

  def self.score(word)
    new(word).score
  end
end



class ETL

  def self.table
    simple_lookup = {
        1 => %W(A E I O U L N R S T),
        2 => %W(D G),
        3 => %W(B C M P),
        4 => %W(F H V W Y),
        5 => %W(K),
        8 => %W(J X),
        10 => %W(Q Z),
    }
    transform(simple_lookup)
  end


  def self.transform(old)
    new_hash = {}
    old.each do |value,letters|
      letters.each do |letter|
        new_hash[letter.downcase] = value
      end
    end
    new_hash
  end

end
