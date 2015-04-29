class String
  def blank?
    self.gsub(/ /, '').empty?
  end
end

class Bob
  def hey(str)
    return_string = 'Whatever.'
    if str.to_s.blank?
      return_string = 'Fine. Be that way!'
    elsif str == str.upcase
      return_string = "Woah, chill out!"
    elsif str[-1] == '?'
      return_string = 'Sure.'
    end
    return_string
  end
end
