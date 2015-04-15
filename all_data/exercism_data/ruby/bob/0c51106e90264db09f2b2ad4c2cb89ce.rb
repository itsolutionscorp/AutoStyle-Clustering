class Bob
  def hey(sentence)
    if sentence.gsub(/[^a-zA-Z]+/, '').match(/^[A-Z]+$/)
      'Woah, chill out!'
    elsif sentence.match(/\?\Z/)
      'Sure.'
    elsif sentence.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
