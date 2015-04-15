class Bob

  def hey(remark)
    silent?(remark, 'Fine. Be that way!')  ||
      shouting?(remark, 'Whoa, chill out!') ||
      question?(remark, 'Sure.') ||
      answer('Whatever.')
  end

  def silent?(remark, response)
    answer(response) if remark.strip.empty?
  end

  def question?(remark, response)
    answer(response) if remark.end_with?('?')
  end

  def shouting?(remark, response)
    answer(response) if remark =~ /[A-Z]/ && remark == remark.upcase
  end

  def answer(response)
    response
  end
end
