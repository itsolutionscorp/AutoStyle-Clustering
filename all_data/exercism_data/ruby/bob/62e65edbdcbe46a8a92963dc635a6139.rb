module StringValidations

  def contains_hyphens_and_period_at_end?(str)
    str.match(/\-|\.$/)
  end

  def contains_uppercase_words?(str)
    str.match(/[A-Z]{2,}/)
  end

  def contains_question_mark?(str)
    str.match(/\?$/)
  end

  def contains_exclamation_mark?(str)
    str.match(/\!$/)
  end

  def contains_ok?(str)
    str.match(/OK/)
  end
end
