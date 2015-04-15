class Bob

  def hey(arg)
    Responder.new(arg).response
  end

end

class Response
  
  def self.shout
    "Woah, chill out!"
  end
  
  def self.question
    "Sure."
  end
  
  def self.blank
    "Fine. Be that way!"
  end
  
  def self.default
    "Whatever."
  end

end

class Responder
  
  def initialize(arg)
    @arg = arg
  end

  def response
    if is_shouting?
      Response.shout
    elsif is_question?
      Response.question
    elsif is_blank?
      Response.blank
    else
      Response.default
    end
  end

  def only_alpha_numeric
    @arg.tr('^A-Za-z0-9', '')
  end

  def only_capitol_letters_and_numbers
    @arg.tr('^A-Z0-9','')
  end

  def only_numbers
    @arg.tr('^0-9','')
  end

  def all_caps?
    only_alpha_numeric == only_capitol_letters_and_numbers
  end

  def only_numbers?
    only_alpha_numeric == only_numbers && !is_blank?
  end

  def no_letters?
    only_numbers? || is_blank?
  end

  def is_blank?
    only_alpha_numeric.empty?
  end

  def last_character
    @arg[-1,1]
  end
  
  def is_shouting?
    all_caps? unless no_letters?
  end

  def is_question?
    last_character == "?" && !is_shouting?
  end

end
