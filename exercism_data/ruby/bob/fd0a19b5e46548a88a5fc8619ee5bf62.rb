class Bob

  def hey(remark)
    silent?(remark, 'Fine. Be that way!')  ||
      shouting?(remark, 'Whoa, chill out!') ||
      question?(remark, 'Sure.') ||
      answer('Whatever.')
  end

  def silent?(remark, response)
    answer(response) if remark.match(/\A\s*\z/)
  end

  def question?(remark, response)
    answer(response) if remark.match(/.\?\z/)
  end

  def shouting?(remark, response)
    clean_remark = remark.gsub(/\d|\W/, '\1 \2')
    answer(response) if !clean_remark.match(/\A\s*\z/) &&
                        (clean_remark == clean_remark.upcase)
  end

  def answer(response)
    response
  end
end
