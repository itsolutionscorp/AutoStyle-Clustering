class Phrase
  
  def initialize( sentence )
    @words = sentence.downcase
  end

  def word_count
    remove_non_letters
    @words.count_uniq
  end


  def remove_non_letters
    letter_array = []
    @words.chars.each do |x| 
      if x[/[^a-zA-Z1-9' ]/].nil? 
        letter_array << x 
      else
        letter_array << " "
      end
    end
    @words = letter_array.join.split.join(" ")
    self
  end

  class ::String
    def count_uniq
      unique = self.split.uniq
      count_output = {}
      (0...unique.length).each do |x| 
        count_output.merge!( Hash[ unique[x], self.split.count( unique[x] ) ] )
      end
      return count_output
    end
  end
end
