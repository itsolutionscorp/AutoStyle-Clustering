class Bob
  def hey(str)
    return 'Fine. Be that way!' if str.gsub(/\s/, '').empty?

    if all_caps?(str)
      'Woah, chill out!'
    elsif question?(str)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def all_caps?(str)
    /^[A-Z]+$/ =~ str.gsub(/[\W\d\s]/, '')
  end

  def question?(str)
    str.end_with?('?')
  end
end
