def combine_anagrams(words)
  groups = Array.new
  semaphore = false
  words.each{ |x|
    semaphore = false
    #print x.sum
    #print " "
    if groups.length.eql? 0
      groups.push([x])
    else
      groups.each { |y|
        #for index in 0 ... y.length
        y.length.times do |index|
          print y[index]
          print "\n"
          if ((y[index].to_s).downcase).sum == (x.downcase).sum
               y.push(x)
               semaphore = true
               break
          end
        end
        print "____________"
        print "\n"
      }
      if !semaphore
        groups.push([x])
      end

    end
    #print groups
    #print "\n"
  }
  print groups
  return groups
end