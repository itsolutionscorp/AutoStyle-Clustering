class Bob

  REPONSES = {
    empty: "Fine. Be that way!",
    yieling: 'Woah, chill out!',
    question: "Sure."
  }

  def hey what
    text_type =  Analiser.string_type what
    REPONSES.fetch text_type, "Whatever."
  end
end

module Analiser

  def self.string_type text
    text = text.gsub(/\d/, '')
    case
    when empty?(text) then :empty
    when yieling?(text) then :yieling
    when question?(text) then :question
    end
  end

  def self.empty? text
    text.strip == ''
  end

  def self.yieling? text
    text =~ /\w/ && text == text.upcase
  end

  def self.question? text
    text[-1]['?']
  end
end
