puts 'Hi, I\'m Bob.'

while(1) do
  print 'What do you want to say to Bob: '
  response = gets.chomp
  if response.size == 0
    puts 'Fine. Be that way!'
  elsif response[-1] == '?'
    puts 'Sure.'
  elsif response.upcase == response
    puts 'Woah, chill out!'
  else
    puts 'Whatever.'
  end
end
