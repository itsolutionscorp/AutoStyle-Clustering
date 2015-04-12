class Phrase
  
  @@phrase_stmt = ""
  attr_reader :phrase_stmt

  def initialize(phrase_stmt)
    @@phrase_stmt = phrase_stmt
  end 
  
  def word_count
    w_hash = Hash.new
    words_arr = @@phrase_stmt.gsub(/[^0-9A-Za-z']/,' ').split.join(" ").split(/\s/).map!(&:downcase)
    words_arr.each { |w|
    if w_hash.has_key?(w)
      w_hash[w] = w_hash[w] + 1
    else
      w_hash[w] = 1
    end
    }
#  return 1
      w_hash
  end  
  
end
