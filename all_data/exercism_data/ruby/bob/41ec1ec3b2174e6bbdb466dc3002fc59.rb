class Bob
  def hey s
    speech = Speech.new \
      text:     s,
      question: 'Sure.',
      yell:     'Woah, chill out!',
      silence:  'Fine. Be that way!',
      default:  'Whatever.'

    speech.question? || speech.yell? || speech.silence? || speech.default
  end
end

class Speech < Struct.new(:text, :question, :yell, :silence, :default)
  def initialize opts
    opts[:text] = opts[:text].tr("\n", ' ')
    opts.each {|k,v| send("#{k}=", v)}
  end

  def question?
    question if text.end_with?('?') && !all_upper?
  end

  def yell?
    yell if all_upper?
  end

  def silence?
    silence if text == '' || all_whitespace?
  end

private
  def alphabetic
    text.gsub(/[^A-Za-z]/, '')
  end

  def all_upper?
    alphabetic.length > 0 && alphabetic.gsub(/[A-Z]/,'').length == 0
  end

  def all_whitespace?
    text =~ /^[ \t]*$/
  end
end
