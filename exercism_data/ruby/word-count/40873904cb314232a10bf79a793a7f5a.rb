class Phrase
  def initialize(str)
    @str = str
  end

  def word_count
  	clean_string_array = @str.gsub(/[^0-9A-Za-z ']/, ' ').split
  	op_hash = Hash.new(0)
    clean_string_array.each_with_object(op_hash) do |str, hash| 
    	hash[str.downcase] += 1
    end
    op_hash
  end
end
