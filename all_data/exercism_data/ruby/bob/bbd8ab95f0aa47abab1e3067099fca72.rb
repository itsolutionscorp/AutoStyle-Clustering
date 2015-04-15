class Bob

  CONDITIONS = [:silence, :shout, :question, :default].freeze

  def hey(input)
    CONDITIONS.each do |condition|
      return send("#{condition}_answer") if send("#{condition}?", input)
    end
  end

  def default?(_)
    true
  end

  def default_answer
    'Whatever.'
  end

  def shout?(input)
    input == input.upcase
  end

  def shout_answer
    'Woah, chill out!'
  end

  def question?(input)
    input.end_with?('?')
  end

  def question_answer
    'Sure.'
  end

  def silence?(input)
    input.strip.empty?
  end

  def silence_answer
    'Fine. Be that way!'
  end
end
