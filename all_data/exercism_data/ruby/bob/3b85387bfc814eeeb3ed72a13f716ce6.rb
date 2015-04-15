#I like this a little more, can't wait for the review

class Teenager
  def hey words
    if empty_or_nil? words
      'Fine. Be that way!'
    elsif shouting? words
      'Woah, chill out!'
    elsif asking_question? words
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Bob < Teenager
end

 private
    def stating_something words
      'Whatever.'
    end
    def shouting? words
      words == words.upcase && !words.empty?
    end
    def asking_question? words
      words.end_with?('?')
    end
    def empty_or_nil? words
      words.to_s == ''
    end
