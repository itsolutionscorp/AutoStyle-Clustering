class Phrase

	def initialize phrase
		@phrase = phrase
	end

	def word_count
		@_word_count ||= Word::Count.new(word_list).count
	end

	def word_list
		@_word_list ||= Word::List.new(@phrase).to_a
	end

	class Word
		class Count

			def initialize list
				@list = list
			end

			def count
				@_count ||= count!
			end

			private

				def count!
					@list.uniq.each do |word|
						counter[word] = @list.count(word)
					end

					return counter
				end

				def counter
					@_counter ||= {}
				end

		end

		class List

			def initialize phrase
				@phrase = phrase
			end

			def to_a
				phrase_with_normalized_character_set.split( /[\s\,]+/ )
			end

			private

				def phrase_with_normalized_character_set
					@phrase.downcase.gsub( /[^a-z0-9\s\,]/, '' )
				end

		end
	end

end
