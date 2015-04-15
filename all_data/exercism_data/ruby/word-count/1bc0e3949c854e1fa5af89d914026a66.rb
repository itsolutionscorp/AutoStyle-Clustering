class Phrase < Struct.new(:phrase)
	
  def word_count
    result = Hash.new
    words.each { |word| result.merge!("#{word}" => "#{words.count(word)}".to_i) }
    result
  end

  private

  def words
    phrase.downcase.gsub(/[[:punct:]]/,'').split(' ')
  end

end
