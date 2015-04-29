class Bob

  def hey text = ''
    answers = {
        sure: 'Sure.',
        whatever: 'Whatever.',
        woah: 'Woah, chill out!',
        fine: 'Fine. Be that way!'
    }
    index = if text.scan(/[[:alpha:]]/).join().match(/^[A-Z]+$/)
              :woah
            elsif text.end_with?('?')
              :sure
            elsif text.strip.size == 0
              :fine
            else
              :whatever
            end
    answers[index]
  end
end
