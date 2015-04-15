class Scrabble
  def self.score(word)
    new(word).score
  end

  def initialize(word)
    self.word = (word || '').downcase
  end

  def score
    word.each_char.inject 0 do |score, char|
      score + char_score(char)
    end
  end

  private

  attr_accessor :word

  def char_score(char)
    case char
    when ?a, ?e, ?i, ?o, ?u, ?l, ?n, ?r, ?s, ?t  then 1
    when ?d, ?g                                  then 2
    when ?b, ?c, ?m, ?p                          then 3
    when ?f, ?h, ?v, ?w, ?y                      then 4
    when ?k                                      then 5
    when ?j, ?x                                  then 8
    when ?q, ?z                                  then 10
    else                                              0
    end
  end
end
