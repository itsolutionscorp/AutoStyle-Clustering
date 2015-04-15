class Bob
  PUNCTUATIONS = {:period => '.', :xmark => '!', :qmark =>'?'}
  RESPONSES = {:statement => 'Whatever.', :exclamation => 'Woah, chill out!', :question => 'Sure.', :silent => 'Fine. Be that way!'}
  
  def initialize
    # TODO:
  end

  def hey(sentence)
    response = 'huh?!'
    multiline_regex = /\n/m

    # flatten multiline string
    sentence.gsub!( multiline_regex, ' ' ).strip! if sentence =~ multiline_regex
    
    # find sentence type
    sentence_type = sentence_type? sentence

    # find response based on sentence type
    RESPONSES.each do |key, val|
      response = val if key == sentence_type
    end
    
    response
  end
  
  private
  
  def sentence_type?(sentence)
    has_all_cap_words = all_cap_words? sentence
    punctuation_type = punctuation_type? sentence
    
    # statement conditions
    sentence_type = :statement if punctuation_type == :period || punctuation_type == :none
    # exclamation conditions
    sentence_type = ( punctuation_type == :xmark && has_all_cap_words ) ? :exclamation : :statement
    sentence_type = :exclamation if punctuation_type == :qmark && has_all_cap_words
    sentence_type = :exclamation if punctuation_type == :none && has_all_cap_words
    # question conditions
    sentence_type = :question if punctuation_type == :qmark && !has_all_cap_words
    # silent conditions
    sentence_type = :silent if sentence.strip.size == 0

    sentence_type
  end

  def punctuation_type?(sentence)
    punct_type = :none

    # look for punctuation type
    PUNCTUATIONS.each do |key, val|
      punct_type = key if val ==  sentence[/.$/]
    end

    punct_type
  end

  def all_cap_words?(sentence)
    punct_regex = /\.$|\!$|\?$/ 
    except_regex = /OK/

    stripped = sentence.clone
    # remove punctuation at end of sentence
    stripped.gsub!( punct_regex, '' ) if stripped =~ punct_regex 
    # downcase exception
    stripped.gsub!( except_regex, stripped[except_regex].downcase ) if stripped =~ except_regex
    
    # look for all cap words aka shouting
    results = stripped.split(' ').inject([]) do |result, word|
      result << ( word.scan(/\p{Upper}/).join.size == word.size ) ? true : false
      result
    end

    results.include? true
  end

end
