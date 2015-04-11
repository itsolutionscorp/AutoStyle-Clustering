class LessReadableTeenager  
  def rules
    raise NotImplementedError
  end

  def hey(comment)
    response(comment)
  end

  def response(comment)
    rules.each do |rule|
      return rule[1] if comment =~ rule[0]
    end
  end
end

class LessReadableBob < LessReadableTeenager
  def rules
    [
      [/\A\s*\z/, "Fine. Be that way!"], # silence
      [/\A[^a-z]*\z/, "Woah, chill out!"], # ALL CAPS
      [/\?\z/, "Sure."], # Question
      [/./, "Whatever."] #catch all
    ]
  end
end

class Teenager
  class Rule
    attr_accessor :matcher, :response

    def initialize(matcher = nil, response = nil)
      self.response = response || "Whatever."
      self.matcher = matcher || /./
    end

    def match?(comment)
      comment =~ matcher
    end

    def self.default_rule
      self.new
    end
  end

  def initialize
    @rules = []
  end

  def hey(comment)
    response(comment)
  end

  def find_hey(comment)
    find_response(comment)
  end

  def find_response(comment)
    (@rules + [Rule.default_rule]).find{|rule| rule.match?(comment)}
  end

  def response(comment)
    (@rules + [Rule.default_rule]).each do |rule|
      return rule.response if rule.match?(comment)
    end
  end
end

class Bob < Teenager
  def initialize
    super
    @rules += [silence?, all_caps?, question?]
  end
  
  def all_caps?
    Rule.new(/\A[^a-z]*\z/, "Woah, chill out!")
  end

  def silence?
    Rule.new(/\A\s*\z/, "Fine. Be that way!")
  end

  def question?
    Rule.new(/\?\z/, "Sure.")
  end
end

# Time Trials
def hey_times(bob, inputs, use_find = false)

  times = []
  5.times do
    startT = Time.now
    inputs.each do |input|
      if use_find
        bob.find_hey(input)
      else
        bob.hey(input)
      end
    end
    endT = Time.now

    times << (endT - startT)
  end
  
  times.each do |t|
    puts "#{t * 1000} ms"
  end

  puts "AVG: #{times.inject(0.0) {|sum, t| sum + t } * 1000 / times.size} ms"
end

comments = ["WHY ARE YOU STILL ASLEEP?", "GET UP", "Please, it's 2pm", "Seriously?"]
bob = Bob.new
inputs = []
rad = Random.new
100000.times do
  inputs << comments[rand(0..3)] # ensures they all get the same input
end

puts "NO FIND"
hey_times(bob, inputs)

puts
puts "FIND"
hey_times(bob, inputs, true)

bob = LessReadableBob.new
puts
puts "LESS READABLE BOB"
hey_times(bob, inputs, false)


=begin SAMPLE TIMES
jonathan:bob jspies$ ruby bob.rb
NO FIND
339.36100000000005 ms
320.65900000000005 ms
365.911 ms
337.38 ms
335.635 ms
AVG: 339.7892 ms

FIND
369.953 ms
340.66999999999996 ms
338.175 ms
332.694 ms
334.543 ms
AVG: 343.20700000000005 ms

LESS READABLE BOB
354.663 ms
340.357 ms
312.727 ms
304.617 ms
290.978 ms
AVG: 320.6684 ms
jonathan:bob jspies$ ruby bob.rb
NO FIND
321.748 ms
326.175 ms
318.923 ms
311.603 ms
334.188 ms
AVG: 322.52740000000006 ms

FIND
339.605 ms
334.51 ms
327.399 ms
349.474 ms
354.07899999999995 ms
AVG: 341.0134 ms

LESS READABLE BOB
314.062 ms
309.508 ms
295.51800000000003 ms
299.863 ms
306.639 ms
AVG: 305.11799999999994 ms
jonathan:bob jspies$ ruby bob.rb
NO FIND
340.252 ms
329.138 ms
319.405 ms
337.028 ms
331.158 ms
AVG: 331.39619999999996 ms

FIND
341.17900000000003 ms
322.08 ms
331.949 ms
335.774 ms
320.693 ms
AVG: 330.335 ms

LESS READABLE BOB
322.671 ms
308.03799999999995 ms
292.104 ms
295.074 ms
319.255 ms
AVG: 307.4284 ms
jonathan:bob jspies$ ruby bob.rb
NO FIND
315.527 ms
314.395 ms
318.061 ms
332.166 ms
312.247 ms
AVG: 318.4792 ms

FIND
344.506 ms
344.45300000000003 ms
324.815 ms
342.329 ms
368.303 ms
AVG: 344.88120000000004 ms

LESS READABLE BOB
302.048 ms
311.00899999999996 ms
344.54 ms
298.514 ms
298.733 ms
AVG: 310.9688 ms
jonathan:bob jspies$ ruby bob.rb
NO FIND
339.57 ms
353.59499999999997 ms
350.32 ms
387.521 ms
310.103 ms
AVG: 348.2218 ms

FIND
337.044 ms
334.28799999999995 ms
349.93 ms
376.475 ms
354.919 ms
AVG: 350.5312 ms

LESS READABLE BOB
325.69599999999997 ms
296.428 ms
336.201 ms
292.785 ms
304.94500000000005 ms
AVG: 311.21100000000007 ms
jonathan:bob jspies$ ruby bob.rb
NO FIND
325.96000000000004 ms
333.62399999999997 ms
319.392 ms
316.458 ms
321.301 ms
AVG: 323.34700000000004 ms

FIND
349.74300000000005 ms
328.52799999999996 ms
350.948 ms
348.686 ms
325.17600000000004 ms
AVG: 340.61620000000005 ms

LESS READABLE BOB
315.43 ms
321.88800000000003 ms
314.063 ms
337.235 ms
316.121 ms
AVG: 320.9474 ms
=end
