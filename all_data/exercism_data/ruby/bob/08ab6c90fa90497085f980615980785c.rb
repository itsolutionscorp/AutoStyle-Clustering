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
  def initialize
    @possible_answers = [
      AnsweringLogic::Yelled,
      AnsweringLogic::Questioned,
      AnsweringLogic::Ignored,
      AnsweringLogic::Ignoring,
    ]

  end

  def hey words
    @possible_answers.find do |answering_logic|
      answering_logic[:logic].call words
    end[:msg]
  end

end
