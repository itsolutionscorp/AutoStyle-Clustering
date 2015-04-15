module AnsweringLogic
  Yelled = {
    msg: 'Woah, chill out!',
    logic: ->(words){ words == words.upcase && words =~ /[a-zA-Z]/ }
  }

  Questioned = {
    msg: 'Sure.',
    logic: ->(words){words[-1] == '?'}
  }

  Ignored = {
    msg: 'Fine. Be that way!',
    logic: ->(words){words.empty? || words =~ /^\s+$/}
  }

  Ignoring = {
    msg: 'Whatever.',
    logic: ->(words){true}
  }

end

class Bob
  def hey words
    possible_answers.find do |answering_logic|
      answering_logic[:logic].call words
    end[:msg]
  end

  def possible_answers
    AnsweringLogic.constants.map {|logic| AnsweringLogic.const_get logic}
  end

end
