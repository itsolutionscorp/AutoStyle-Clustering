class Bob
  ANSWERS = [
    [-> (text) { text =~ /\A\s*\Z/ },                         'Fine. Be that way!'],
    [-> (text) { text =~ /[A-Z]/ && text == text.upcase },    'Whoa, chill out!'],
    [-> (text) { text[-1] == '?' },                           'Sure.']
  ]

  def hey text
    ret = 'Whatever.'
    ANSWERS.each do |e|
      if e.first.call(text)
        ret = e[1]
        break
      end
    end
    ret
  end
end
