class Bob
  def hey(str)
    @phrase = str
    if all_caps?
      "Woah, chill out!"
    elsif question?
      "Sure."
    elsif empty_phrase?
      "Fine. Be that way!"
    else
      'Whatever.'
    end
  end

  private
  def all_caps?
    arr_of_chars = @phrase.split('')
    arr_of_chars.select {|s| s != s.upcase}.empty? && not_only_integers?
  end

  def question?
    @phrase[-1] == '?'
  end

  def not_only_integers?
    @phrase =~ /[A-Z]/
  end

  def empty_phrase?
    arr_of_chars = @phrase.split('')
    arr_of_chars.select {|s| s != ' '}.empty?
  end
end
