class Bob
  MATCHERS = {empty?: 'Fine. Be that way!',
              yelling?: 'Woah, chill out!',
              question?: 'Sure.'}

  def hey(s)
    MATCHERS.each do |f, answer|
      return answer if send(f, s)
    end
    'Whatever.'
  end

  private

  def yelling?(s)
    s.upcase == s
  end

  def question?(s)
    s.chars.last == '?'
  end

  def empty?(s)
    s.nil? or s.empty?
  end
end
