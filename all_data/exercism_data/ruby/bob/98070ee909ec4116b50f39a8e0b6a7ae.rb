class Bob
  def hey s
    @text = s.tr("\n", ' ')
    default = lambda {'Whatever.'}
    [question?, yell?, silence?].detect(default){|x| x}
  end

  def question?
    respond_with 'Sure.' do
      @text.end_with?('?') && !all_upper?
    end
  end

  def yell?
    respond_with 'Woah, chill out!' do
      all_upper?
    end
  end

  def silence?
    respond_with 'Fine. Be that way!' do
      empty_string? || all_whitespace?
    end
  end

private
  def respond_with response, &blk
    resp = yield blk
    if resp
      response
    else
      false
    end
  end

  def alphabetic
    @text.gsub(/[^A-Za-z]/, '')
  end

  def all_upper?
    alphabetic.length > 0 && alphabetic.gsub(/[A-Z]/,'').length == 0
  end

  def all_whitespace?
    @text =~ /^[ \t]*$/
  end

  def empty_string?
    @text == ''
  end
end
