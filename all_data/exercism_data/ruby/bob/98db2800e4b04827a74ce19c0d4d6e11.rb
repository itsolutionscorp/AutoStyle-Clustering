# requires ruby 2.0
class BobInquiry
  attr_reader :str

  def initialize(str)
    @str = str.strip
  end

  def empty?
    str == ''
  end

  def shouting?
    normalized_str = str.encode('UTF8-MAC').chars.select {|c|
      c.ord < 128 }.join
    normalized_str.match(%r{[A-Z]}) && normalized_str.upcase == normalized_str
  end

  def question?
    str.match(%r{\?\z})
  end
end

class Bob
  def hey(inquiry_str)
    inquiry = BobInquiry.new(inquiry_str)
    if inquiry.empty?
      "Fine. Be that way!"
    elsif inquiry.shouting?
      "Woah, chill out!"
    elsif inquiry.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
