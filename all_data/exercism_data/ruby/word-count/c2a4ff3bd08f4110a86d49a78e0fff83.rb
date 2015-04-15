class Phrase
  def initialize(text)
    @text = text
  end
  
  def word_count
    make_dict(spliting(@text))
  end
  
  def spliting(text)
    return text.split if (text =~ /^[^\s]+$/).nil?
    return text.split(',')
  end
  
  def make_dict(list)
    list.map!{|i| del_punctuation(i.downcase) }.reject!{|i| not_word(i)}
    dict = Hash.new
    list.each do |i|
      !dict[i] ? dict[i] = 1 : dict[i] += 1 
    end
    dict
  end
  
  def del_punctuation(word)
    word.match(/[a-z0-9]+(.*[a-z0-9]+)*/).to_s
  end
  
  def not_word(w)
    (w =~ /[a-z0-9]+/).nil?
  end
  
end
