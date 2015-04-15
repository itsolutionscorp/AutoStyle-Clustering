module Statement
  def self.type content
    if content.to_s.empty?
      :silence
    elsif content == content.upcase
      :yelling
    elsif content.end_with? '?'
      :question
    end
  end
end

class Bob
  def initialize
    @default_answer      = "Whatever."

    answer_to[:silence]  = "Fine. Be that way!"
    answer_to[:yelling]  = "Woah, chill out!"
    answer_to[:question] = 'Sure.'
  end

  def hey input
    answer_to[Statement.type(input)]
  end

  def answer_to
    @answer_to ||= Hash.new { @default_answer }
  end
end
