require 'forwardable'

class Proverb
  extend Forwardable

  def_delegators :@args, :items, :original_desire

  def initialize(*wanted_items)
    @args = ProverbArguments.new(wanted_items)
  end

  def to_s
    (item_phrases + [ending_phrase]).join("\n")
  end

private

  def item_phrases
    items.each_cons(2).map(&method(:pair_phrase))
  end

  def pair_phrase((wanted, lost))
    "For want of a #{wanted} the #{lost} was lost."
  end

  def ending_phrase
    "And all for the want of a #{original_desire}."
  end
end

class ProverbArguments
  attr_reader :items, :original_desire

  def initialize(args)
    @items = args
    @original_desire = @items.first

    if has_qualifier?
      @original_desire = [extract_qualifier, @original_desire].join(' ')
    end
  end

private

  def has_qualifier?
    items.last.is_a?(Hash)
  end

  def extract_qualifier
    items.pop[:qualifier]
  end
end
