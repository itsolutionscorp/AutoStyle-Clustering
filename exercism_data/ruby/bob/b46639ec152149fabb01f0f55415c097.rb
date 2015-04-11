class Bob

  MATCHERS = {
    /.*!$/ => "Whoa, chill out!",
    /.*[?]$/ => "Sure.",
    /.*/ => "Whatever." #default should always be last
  }

  def hey(text)
    MATCHERS.detect {|pattern, response| text =~ pattern }.last
  end
end
