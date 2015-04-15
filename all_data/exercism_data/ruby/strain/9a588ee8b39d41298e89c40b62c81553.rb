# If this was a real problem and not just a daily, warmup exercise, this
# is the code that I would ship:
#
#module Enumerable
  #alias_method :keep, :select
  #alias_method :discard, :reject
#end
#
# This leaves less code to have to be maintained while leaving the nice
# method names that we need (keep, discard).
#
# However since I think part of the fun of these exercises is the fun of
# building things, let take a look at some code that does not use the
# select or reject functions built into ruby.  It's like taboo!

module Enumerable
  def keep(&block)
    Array.new.tap do |valid_elements|
      each { |e| valid_elements << e if yield e }
    end
  end

  def discard(&block)
    Array.new.tap do |valid_elements|
      each { |e| valid_elements << e unless yield e }
    end
  end
end
