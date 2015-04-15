class Bob

def hey(message)
    message = message.gsub(/\n/,'')
    case
    when message.match(/^[A-Z\s]*[0-9\W]*[A-Z]+[0-9A-Z\?!\s]*$/) then 'Woah, chill out!'
    when message.match(/^(([A-Z])|\d*)[a-zA-Z0-9 ,\.!]*\?$/) then 'Sure.'
    when message.match(/^\W*$/) then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end
end
