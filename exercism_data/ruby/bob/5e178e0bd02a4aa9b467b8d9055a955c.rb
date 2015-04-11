class Bob

  def initialize
  end

  def hey(string)
    string.gsub!(' ', '')
    string.gsub!(',', '')
    if string.empty?
      'Fine. Be that way!'
    elsif string == string.upcase && string.match(/.\D+[^?]/)
      'Woah, chill out!'
    elsif string.match /.+\?\z/
      'Sure.'
    else
      "Whatever."
    end
  end

end
