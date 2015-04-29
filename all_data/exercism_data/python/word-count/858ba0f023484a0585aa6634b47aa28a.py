def word_count(phrase):
    
    final_count = {}

    words = phrase.split()
    
    def increase_count_in_final_count(word_to_increment):
        final_count[word_to_increment] = final_count[word_to_increment] + 1

    def add_word_to_final_count(word_to_add):
        final_count[word_to_add] = 1


    for word in words:
        if word in final_count:
            increase_count_in_final_count(word)
        else:
            add_word_to_final_count(word)

    return final_count
