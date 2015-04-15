class Phrase  
  attr_reader :word_count
  
  def initialize(string)
    # strip out any daft characters but keep commas & apostrophes (for certain tests)
    string = string.gsub(/[^\',a-z0-9 -]/i, '')
   
    @word_count = string.split(/[\s,]+/).inject(Hash.new(0)) { |hash,value| 
      # convert all strings to lower case for certain tests
      hash[value.downcase] += 1
      hash
    }    
  end
end

# Phrase.new("car : carpet as java : javascript!!&@$%^&")
