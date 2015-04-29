class Bob
  def hey(str)
    @str = strip_ws str
    if silence?
      'Fine. Be that way!'
    elsif is_a_question?
      'Sure.'
    elsif is_yelling?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  def is_a_question?
    has_interogation_mark? && !all_caps?
  end

  def is_yelling?
    all_caps?
  end

  def has_interogation_mark?
    @str.end_with? '?'
  end

  def has_exclamation_mark?
    @str.end_with? '!'
  end

  def all_caps?
    str = filter_acronyms @str
    (str == str.upcase) && @str.match(/[a-zA-Z]/)
  end

  def only_numbers?
    @str.match /[a-zA-Z]/
  end

  def filter_acronyms(str)
    split_special_chars(@str)
      .reject { |word| is_acronym? word }
      .join ' '
  end

  def split_special_chars(str)
    # whitespace, dot, comma, apostrophe
    str.split /[\s\.\,\']/
  end

  def is_acronym?(str)
    Acronyms.include? str
  end

  def silence?
    !(@str.count('a-zA-Z0-9') > 0)
  end

  def strip_ws(str)
    str.gsub(/\s+/, '')
  end
end

class Acronyms
  LIST = %w( DMV )
  def self.include?(str)
    LIST.include?(str)
  end
end
