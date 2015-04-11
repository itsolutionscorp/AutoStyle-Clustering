require 'pry'
# https://xkcd.com/208/
class Bob
  def hey(s)
    if aint_said_nothin(s)
      "Fine. Be that way!"
    elsif too_loud(s)
      "Woah, chill out!"
    elsif nag_nag_nag(s)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def aint_said_nothin(s)
    s.strip.empty?
  end

  def too_loud(s)
    (s =~ /\A[^a-z]+\Z/)
  end

  def nag_nag_nag(s)
    s[-1] == '?'
  end
end
